package zuhowks.asiluxteam.fr.launcher.auth.mineweb;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import zuhowks.asiluxteam.fr.launcher.ALauncher;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicReference;

public class AsiluxAuthMineweb {
    private ALauncher launcher = ALauncher.getInstance();
    private boolean connected = false;
    private String pseudo;
    private String hash;


    public AsiluxAuthMineweb(String pseudo, String password) {
        try {
            URLConnection connection = (new URL(launcher.getWebSiteURL() + "/auth/start?username="+pseudo+"&password="+getSHA256(password)).openConnection());
            connection.setRequestProperty("User-Argent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            InputStream is = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String inputLine;
            while ((inputLine = in.readLine()) != null){
                switch (inputLine){
                    case "success_ok":
                        ALauncher.getInstance().getLogger().info("Connexion OK.");
                        setHash(getSHA256(password));
                        setPseudo(pseudo);
                        setConnected(true);
                        break;
                    case "error_password":
                        ALauncher.getInstance().getLogger().err("Erreur: invalid Password / Username.");
                    default:
                        break;
                }

            }
            in.close();

        } catch (Exception e){
            ALauncher.getInstance().getLogger().warn(e.getMessage());
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
                    JsonElement jsonElement = JsonParser.parseString(inputLine);
                    return String.valueOf(
                            jsonElement.getAsJsonObject().get(info))
                            .replaceAll("\"", "");
                }
            } catch (Exception e) {
                ALauncher.getInstance().getLogger().warn(e.getMessage());
            }
        }
        else{
            ALauncher.getInstance().getLogger().warn("Vous n'êtes pas connecté.");
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