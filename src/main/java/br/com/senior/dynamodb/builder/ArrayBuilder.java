package br.com.senior.dynamodb.builder;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArrayBuilder {

    List<Object> array;

    public static ArrayBuilder oneArray(){
        ArrayBuilder builder = new ArrayBuilder();
        builder.array = new ArrayList<>();
        return builder;
    }

    public ArrayBuilder put(Object value){
        this.array.add(value);
        return this;
    }

    public List<Object> toArray(){
        return array;
    }
}
