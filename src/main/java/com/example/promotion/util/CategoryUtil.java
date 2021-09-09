/**
 * @author: Samuel Bazoalto
 */
package com.example.promotion.util;

public class CategoryUtil {

    private boolean sanitized;
    private String code;

    public CategoryUtil(){
        this.sanitized = false;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSanitized(boolean sanitized) {
        this.sanitized = sanitized;
    }

    public boolean getSanitized(){
        return this.sanitized;
    }

    public boolean validate() throws CheckedException {
        if(this.code.length() < 5) {
            throw new CheckedException();
        }
        else{
            return true;
        }
    }

    public boolean validateUnchecked() throws UncheckedException {
        if(this.code.length() < 5) {
            throw new UncheckedException();
        }
        else{
            return true;
        }
    }

}
