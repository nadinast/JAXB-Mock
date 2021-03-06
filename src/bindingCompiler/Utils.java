package bindingCompiler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {


    private final String classTemplate =
            "package generatedClasses;\n" +
            "import java.util.ArrayList;\n\n" +
            "public class {CLASS_NAME} {\n" +
            "{FIELD_DECL}" +
            "{GETTER}" +
            "{SETTER}" +
            "}";



    private final String fieldDeclarationTemplate = "   private {FIELD_TYPE} {FIELD_NAME};\n";
    private final String getterTemplate =
            "\n   public {FIELD_TYPE} get{FIELD_NAME}(){\n" +
            "       return this.{FIELD_NAME};\n" +
            "   }\n";

    private final String setterTemplate =
            "\n   public void set{FIELD_NAME}({FIELD_TYPE} {FIELD_NAME}){\n" +
                    "       this.{FIELD_NAME} = {FIELD_NAME};\n" +
                    "   }\n";

    public String getSetterTemplate() {
        return setterTemplate;
    }

    public String getFieldDeclarationTemplate() {
        return fieldDeclarationTemplate;
    }

    public String getClassTemplate() {
        return classTemplate;
    }

    public String getGetterTemplate() {
        return getterTemplate;
    }

    public boolean createFile(File sourceFile){
        try {
            if (sourceFile.createNewFile()){
                return true;
            }else{
                return false;
            }

        } catch (IOException e) {
            return false;
        }
    }

    public void writeToFile(String fileContents, File file) {
        try {

            BufferedOutputStream buf = new BufferedOutputStream(new FileOutputStream(file));
            byte[] contentBytes = fileContents.getBytes();
            System.out.println(file.getCanonicalPath());
            buf.write(contentBytes);
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
