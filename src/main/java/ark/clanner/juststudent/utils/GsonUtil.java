package ark.clanner.juststudent.utils;

import com.google.gson.Gson;

/**
 * Created by Clanner on 2018/5/3.
 */
public class GsonUtil {

    private GsonUtil(){}

    public static Gson getInstance(){
        return Holder.instance;
    }

    private static final class Holder{
        private static final Gson instance = new Gson();
    }
}
