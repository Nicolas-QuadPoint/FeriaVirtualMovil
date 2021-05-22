package com.feriantes4dawin.feriavirtualmovil.data;

import com.feriantes4dawin.feriavirtualmovil.data.model.LoggedInUser;

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
public class Result<T> {

    public class Success<T> extends Result{
        public T data;
        public Success(){ }
        public Success(T d){
            this.data = d;
        }
    }

    public class Error<T> extends Result{
        public Exception exception;
        public Error(){}
        public Error(Exception ex){
            this.exception = ex;
        }
    }

    @Override
    public String toString(){
        if(this instanceof  Success){
            return "Success[data=$data]";
        }
        else if(this instanceof Error){
            return "Error[exception=$exception]";
        }
        else{
            return "Unkwown type";
        }
    }
}