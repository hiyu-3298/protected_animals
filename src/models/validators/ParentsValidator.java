package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Parents;
import utils.DBUtil;

public class ParentsValidator {
    public static List<String> validate(Parents e,Boolean codeDuplicateCheckFlag,Boolean passwordCheckFlag){
        List<String> errors = new ArrayList<String>();

        String code_error = validateCode(e.getCode(),codeDuplicateCheckFlag);
        if(!code_error.equals("")){
            errors.add(code_error);
        }

        String name_error = validateName(e.getName());
        if(!name_error.equals("")){
            errors.add(name_error);
        }

        String password_error = validateName(e.getPassword());
        if(!password_error.equals("")){
            errors.add(password_error);
        }
        return errors;
    }

    //社員番号
    private static String validateCode(String code,Boolean codeDuplicateCheckFlag){
        //必須入力チェック
        if(code == null || code.equals("")){
            return "登録番号を入力してください。";
        }
        //すでに登録されている番号との重複チェック
        if(codeDuplicateCheckFlag){
            EntityManager em = DBUtil.createEntityManager();
            long parents_count = (long)em.createNamedQuery("checkRegisteredCode",Long.class)
                    .setParameter("code",code)
                    .getSingleResult();
            em.close();
            if(parents_count > 0){
                return "入力された登録番号はすでに登録されています。";
            }

        }
        return "";
    }

    //氏名の必須入力
    private static String validateName(String name){
        if(name == null || name.equals("")){
            return "氏名を入力してください。";
        }
        return "";
    }

    //パスワード入力チェック
    private static String validatePassword(String password,Boolean passwordCheckFlag){
        //パスワード変更時のみ入力
        if(passwordCheckFlag && (password == null || password.equals(""))){
            return "パスワードを入力してください。";
        }
        //入力文字数制限
        if(password.length() < 8){
            return "パスワードは8文字以上で入力してください。";
        }
        return "";
    }

}
