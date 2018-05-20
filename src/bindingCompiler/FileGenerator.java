package bindingCompiler;

import java.util.ArrayList;
import java.util.List;

public class FileGenerator {
    private List<JavaFile> fileList = new ArrayList<>();
    private int size = 0;

    public void addFile(String className) {
        fileList.add(new JavaFile(className));
        size++;
    }

    public void removeFile() {
        fileList.get(size - 1).writeFile();
        fileList.remove(size - 1);
        size--;
    }

    public void addAttributes(String type, String name){
        fileList.get(size - 1).addFields(type, name);
    }
}
