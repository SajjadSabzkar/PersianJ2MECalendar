package Reverse;

import java.io.DataInputStream;
import java.io.IOException;

public final class ResourceVerifier {

    private static DataInputStream dataInputStream;

    private static String appIconResource;

    private static byte[] byteArray = new byte[10];

    private static String authorIconResource;

    private static String helpIconResource;

    private static String weekDayIconResource;

    private static String appIconHash;

    private static String authorIconHash;

    private static String helpIconHash;

    private static String weekDayIconHash;

    public static boolean setDensity(int density) {
        String hash = "";
        boolean changed = false;

        switch (density) {
            case 160:
                appIconResource = "/160/B.png";
                weekDayIconResource = "/160/S.png";
                authorIconResource = "/160/L.png";
                helpIconResource = "/160/H.png";
                appIconHash = "-104121-11242-44-5842389698";
                weekDayIconHash = "-4323-19-13-1375-9-18-103-20";
                authorIconHash = "62-10899-5262-50030-113";
                helpIconHash = "32-3053-124171458682-16";
                break;
            case 208:
                appIconResource = "/208/B.png";
                weekDayIconResource = "/208/S.png";
                authorIconResource = "/220/L.png";
                helpIconResource = "/220/H.png";
                appIconHash = "111121414-10-2327-11105-32";
                weekDayIconHash = "1714-79-19-12734-1-54-102-25";
                authorIconHash = "-57-511491-7753-107-59-122-104";
                helpIconHash = "126-67-108-355-89112-196-100";
                break;
            case 220:
                appIconResource = "/220/B.png";
                weekDayIconResource = "/220/S.png";
                authorIconResource = "/220/L.png";
                helpIconResource = "/220/H.png";
                appIconHash = "-11068101-48-960-931047580";
                weekDayIconHash = "88-531666-87-34118-245523";
                authorIconHash = "-57-511491-7753-107-59-122-104";
                helpIconHash = "126-67-108-355-89112-196-100";
                break;
            case 320:
                appIconResource = "/320/B.png";
                weekDayIconResource = "/320/S.png";
                authorIconResource = "/320/L.png";
                helpIconResource = "/320/H.png";
                appIconHash = "1-40-60-60-600108989898";
                weekDayIconHash = "-43-75-1783120-3781-44-98-106";
                authorIconHash = "-42351053-4-42-1021920-65";
                helpIconHash = "-9491-808120-71395183-56";
                break;
            default:
                return false; // Unsupported density
        }

        try {
            dataInputStream = new DataInputStream(ResourceVerifier.class.getResourceAsStream(authorIconResource));
            dataInputStream.skip(1024);
            dataInputStream.read(byteArray, 0, 10);
            for (int i = 0; i < byteArray.length; i++) {
                hash = new StringBuffer().append(hash).append(String.valueOf((int) byteArray[i])).toString();
            }
            dataInputStream.close();
            changed = !hash.equals(authorIconHash);

            hash = "";
            dataInputStream = new DataInputStream(ResourceVerifier.class.getResourceAsStream(appIconResource));
            dataInputStream.skip(2024);
            dataInputStream.read(byteArray, 0, 10);
            for (int i = 0; i < byteArray.length; i++) {
                hash = new StringBuffer().append(hash).append(String.valueOf((int) byteArray[i])).toString();
            }
            dataInputStream.close();
            if (!hash.equals(appIconHash)) {
                changed = true;
            }

            hash = "";
            dataInputStream = new DataInputStream(ResourceVerifier.class.getResourceAsStream(helpIconResource));
            dataInputStream.skip(2024);
            dataInputStream.read(byteArray, 0, 10);
            for (int i = 0; i < byteArray.length; i++) {
                hash = new StringBuffer().append(hash).append(String.valueOf((int) byteArray[i])).toString();
            }
            dataInputStream.close();
            if (!hash.equals(helpIconHash)) {
                changed = true;
            }

            hash = "";
            dataInputStream = new DataInputStream(ResourceVerifier.class.getResourceAsStream(weekDayIconResource));
            dataInputStream.skip(2024);
            dataInputStream.read(byteArray, 0, 10);
            for (int i = 0; i < byteArray.length; i++) {
                hash = new StringBuffer().append(hash).append(String.valueOf((int) byteArray[i])).toString();
            }
            dataInputStream.close();
        } catch (IOException e) {
            Main.instance.quit();
            return changed;
        }

        if (changed) {
            Main.instance.quit();
        }

        return changed;
    }
}
