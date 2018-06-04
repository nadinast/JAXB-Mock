package bindingCompiler;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class JavaFile {
    private File file;
    private String fileTemplate;
    private Utils utils;
    private String fieldDecl;
    private String getter;
    private String setter;

    public JavaFile(String fileName) {
        utils = new Utils();
        fileTemplate = utils.getClassTemplate();
        fieldDecl = utils.getFieldDeclarationTemplate();
        getter = utils.getGetterTemplate();
        setter = utils.getSetterTemplate();

        createJavaFile(fileName);
    }

    public void createJavaFile(String className) {
        file = new File("src\\generatedClasses\\" + className + ".java");
        utils.createFile(file);
        fileTemplate = utils.getClassTemplate();
        fileTemplate = fileTemplate.replaceAll("\\{CLASS_NAME}", className);
    }

    public void addFields(String fieldType, String fieldName){
        fileTemplate = fileTemplate.replace("{FIELD_DECL}", fieldDecl + "{FIELD_DECL}");
        fileTemplate = fileTemplate.replace("{GETTER}", getter + "{GETTER}");
        fileTemplate = fileTemplate.replace("{SETTER}", setter + "{SETTER}");

        fileTemplate = fileTemplate.replace("{FIELD_TYPE}", fieldType);
        fileTemplate = fileTemplate.replace("{FIELD_NAME}", fieldName);
    }


    public void writeFile() {
        removeTemplates();
        utils.writeToFile(fileTemplate, file);
        /*JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, file.getPath());
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { file.toURI().toURL() });
            Class<?> cls = Class.forName("generatedClasses."+file.getName().replace(".java", ""), true, classLoader);
        } catch (MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    private void removeTemplates() {
        fileTemplate = fileTemplate.replace("{FIELD_DECL}", "");
        fileTemplate = fileTemplate.replace("{GETTER}", "");
        fileTemplate = fileTemplate.replace("{SETTER}", "");
        fileTemplate = fileTemplate.replace("{PARAM}", "");
    }
}