package com.five.hallSitting.utils;

import com.five.hallSitting.model.OneSit;
import com.five.hallSitting.model.Sits;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by haoye on 17-6-6.
 */
public class SitUtil {
    public static Sits convertClass(String sitStr) {
        JSONArray json = JSONArray.fromObject(sitStr);
        OneSit[] oneSits = new OneSit[json.size()];
        boolean notFullFlag = false;
        if(json.size()>0){
            for(int i = 0 ;i < json.size(); i++){
                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                int x = (int) job.get("x");
                int y = (int) job.get("y");
                Object validObj = job.get("valid");
                boolean valid = (validObj == null)? false : (boolean)validObj;
                if (valid) {
                    notFullFlag = true;
                }
                OneSit oneSit = new OneSit(x, y, valid);
                oneSits[i] = oneSit;
            }
        }
        return new Sits(!notFullFlag, oneSits);
    }
}
