package ark.clanner.juststudent.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Clanner on 2018/3/9.
 */
public abstract class BaseController {

    @Autowired
    private Response response;

    protected Response success(String message) {
        return success(message, null, null);
    }

    protected Response success(String message, Object object) {
        return success(message, object, null);
    }

    protected Response success(String message, Object object, List<Object> list) {
        response.setCode(20000);
        response.setMessage(message);
        response.setData(object);
        response.setDataList(list);
        return response;
    }

    protected Response failure(String message) {
        return failure(message, null, null);
    }

    protected Response failure(String message, Object object) {
        return failure(message, object, null);
    }

    protected Response failure(String message, List<Object> list) {
        return failure(message, null, list);
    }

    protected Response failure(String message, Object object, List<Object> list) {
        response.setCode(40000);
        response.setMessage(message);
        response.setData(object);
        response.setDataList(list);
        return response;
    }

    /**
     * @param user_id 用户id
     * @param id      保存类型id,如
     * @param file    保存文件
     * @param request HttpServletRequest
     * @param path    保存路径
     * @return
     */
    protected boolean saveImage(Integer user_id, int id, MultipartFile file, HttpServletRequest request, String path) {
        if (!file.isEmpty()) {
            final String separator = File.separator;
            String[] s = file.getOriginalFilename().split("\\.");
            StringBuffer stringBuffer = new StringBuffer();
            //文件保存路径
            stringBuffer.append(request.getSession().getServletContext().getRealPath(separator + path + separator));
            stringBuffer.append(separator);
            stringBuffer.append(user_id);
            if (id != 0) {
                stringBuffer.append(separator);
                stringBuffer.append(id);
            }
            stringBuffer.append(separator);
            stringBuffer.append(s[0]);
            stringBuffer.append(".jpg");
            String filePath = stringBuffer.toString();
            try {
                File f = new File(filePath);
                f.setWritable(true, false);
                if (!f.isDirectory()) {
                    f.mkdirs();
                }
                file.transferTo(f);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void deleteFile(HttpServletRequest request, String dir, int user_id, int id) {
        final String separator = File.separator;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(request.getSession().getServletContext().getRealPath(separator + dir + separator));
        stringBuffer.append(separator);
        stringBuffer.append(user_id);
        stringBuffer.append(separator);
        stringBuffer.append(id);
        File file = new File(stringBuffer.toString());
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) files[i].delete();
            file.delete();
        }
    }
}
