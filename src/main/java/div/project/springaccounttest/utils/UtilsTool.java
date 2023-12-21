package div.project.springaccounttest.utils;

import java.util.UUID;

public  class UtilsTool {
    public  static String generateUUID(){
        // 生成新的UUID
        UUID uuid = UUID.randomUUID();

        // 將UUID轉換為字符串表示形式
        String uuidString = uuid.toString();
        return  uuidString;
    }
}
