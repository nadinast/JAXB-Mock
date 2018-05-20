import java.io.File;

public class JavaFile {
    private File file;
    private String fileTemplate;
    private Utils utils;
    private String fieldDecl;
    private String param;
    private String constructorAssign;
    private String getter;

    public JavaFile(String fileName) {
        utils = new Utils();
        fileTemplate = utils.getClassTemplate();
        fieldDecl = utils.getFieldDeclarationTemplate();
        param = utils.getConstructorParamTemplate();
        constructorAssign = utils.getConstructorAssignTemplate();
        getter = utils.getGetterTemplate();

        createJavaFile(fileName);
    }

    public void createJavaFile(String className) {
        file = new File(className + ".java");
        utils.createFile(file);
        fileTemplate = utils.getClassTemplate();
        fileTemplate = fileTemplate.replaceAll("\\{CLASS_NAME}", className);
    }

    public void addFields(String fieldType, String fieldName){
        fileTemplate = fileTemplate.replace("{FIELD_DECL}", fieldDecl + "{FIELD_DECL}");
        fileTemplate = fileTemplate.replace("{PARAM}", param + "{PARAM}");
        fileTemplate = fileTemplate.replace("{CONSTRUCTOR_ASSIGN}", constructorAssign + "{CONSTRUCTOR_ASSIGN}");
        fileTemplate = fileTemplate.replace("{GETTER}", getter + "{GETTER}");

        fileTemplate = fileTemplate.replace("{FIELD_TYPE}", fieldType);
        fileTemplate = fileTemplate.replace("{FIELD_NAME}", fieldName);
    }


    public void writeFile() {
        removeTemplates();
        utils.writeToFile(fileTemplate, file);
    }

    private void removeTemplates() {
        fileTemplate = fileTemplate.replace("{FIELD_DECL}", "");
        fileTemplate = fileTemplate.replace(", {PARAM}", "");
        fileTemplate = fileTemplate.replace("{CONSTRUCTOR_ASSIGN}", "");
        fileTemplate = fileTemplate.replace("{GETTER}", "");
        fileTemplate = fileTemplate.replace("{PARAM}", "");
    }
}