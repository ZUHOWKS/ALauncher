package dev.asiluxserver.launcher.auth.mineweb;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.asiluxserver.launcher.Launcher;
import dev.asiluxserver.launcher.Main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class AuthMineWeb {
    private boolean connected = false;
    private String pseudo;
    private String hash;


    public AuthMineWeb (String pseudo, String password) {
        try {
            URLConnection connection = (new URL("https://asilux.w4.cmws.fr/auth/start?username="+pseudo+"&password="+getSHA256(password)).openConnection());
            connection.setRequestProperty("User-Argent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            InputStream is = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String inputLine;
            while ((inputLine = in.readLine()) != null){
                switch (inputLine){
                    case "success_ok":
                        Launcher.getInstance().getLogger().info("Connexion OK.");
                        setHash(getSHA256(password));
                        setPseudo(pseudo);
                        setConnected(true);
                        break;
                    case "error_password":
                        Launcher.getInstance().getLogger().err("Erreur: invalid Password / Username.");
                    default:
                        break;
                }

            }
            in.close();

        } catch (Exception e){
            Launcher.getInstance().getLogger().warn(e.getMessage());
        }
    }

    public String getInfos(String info) {
        if (isConnected()){
            try{
                URLConnection connection = (new URL("https://asilux.w4.cmws.fr/auth/getDataLauncher?username="+pseudo+"&password="+getHash()).openConnection());
                connection.setRequestProperty("User-Argent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                connection.connect();
                InputStream is = connection.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    JsonObject jsonObject = (JsonObject) new JsonParser().parse(inputLine);
                    return String.valueOf(jsonObject.get(info)).replaceAll("\"", "");
                }
            } catch (Exception e) {
                Launcher.getInstance().getLogger().warn(e.getMessage());
            }
        }
        else{
            Launcher.getInstance().getLogger().warn("Vous n'êtes pas connecté.");
        }
        return "";
    }

    public String getSHA256(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        AtomicReference<StringBuffer> stringBuffer = new AtomicReference<>(new StringBuffer());
        for (byte b: hash){
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1)stringBuffer.get().append('0');
            stringBuffer.get().append(hex);
        }
        return stringBuffer.toString();
    }


    /* Getter */
    public boolean isConnected() { return connected; }
    public String getPseudo() { return pseudo; }
    public String getHash() { return hash; }


    /* Setter !PRIVATE! */
    private void setConnected(boolean connected) { this.connected = connected; }
    private void setPseudo(String pseudo) { this.pseudo = pseudo; }
    private void setHash(String hash) { this.hash = hash; }
}
