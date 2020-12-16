package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Animals;

public class AnimalsValidator {
    public static List<String> validate(Animals r) {
        List<String> errors = new ArrayList<String>();

        String name_error = _validateName(r.getName());
        if (!name_error.equals("")) {
            errors.add(name_error);
        }

        String content_error = _validateContent(r.getContent());
        if (!content_error.equals("")) {
            errors.add(content_error);
        }

        String sex_error = _validateSex(r.getSex());
        if (!sex_error.equals("")) {
            errors.add(sex_error);
        }

        return errors;
    }

    private static String _validateName(String name) {
        if (name == null || name.equals("")) {
            return "名前を入力してください。";
        }

        return "";
    }

    private static String _validateContent(String content) {
        if (content == null || content.equals("")) {
            return "内容を入力してください。";
        }

        return "";
    }

    private static String _validateSex(String sex) {
        if (sex == null || sex.equals("")) {
            return "性別を入力してください。";
        }

        return "";
    }

}
