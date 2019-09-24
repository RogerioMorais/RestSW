package com.b2w.poc.api.responses;

import java.util.List;

public class response<T> {
    private T data;
    private List<String> erros;

    public response(){
        this.data=null;
        this.erros=null;
    }
    public response(T data){
        this.data=data;
    }
    public response(List<String> erros){
        this.erros=erros;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}
