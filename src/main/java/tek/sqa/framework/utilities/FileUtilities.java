package tek.sqa.framework.utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtilities {

    public static FileInputStream getFileInputSteam(String filePath) throws IOException {
            return new FileInputStream(new File(filePath));
    }
}
