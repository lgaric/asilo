package com.puppy.asilo.Controller;

public class User {

    /**
     * Entity variables.
     * */

    private CharSequence name;
    private CharSequence surname;
    private CharSequence email;
    private CharSequence password;
    private CharSequence phone;

    /**
     * Entity class Setters
     * */

    public final void setName(CharSequence name){
        this.name = name;
    }

    public final void setSurname(CharSequence surname){
        this.surname = surname;
    }

    public final void setEmail(CharSequence email){
        this.email = email;
    }

    public final void setPassword(CharSequence password){
        this.password = password;
    }

    public final void setPhone(CharSequence phone){
        this.phone = phone;
    }


    /**
     * Entity class Getters.
     * */

    public final CharSequence getName(){
        return this.name;
    }

    public  final CharSequence getSurname(){
        return this.surname;
    }

    public  final CharSequence getEmail(){
        return this.email;
    }

    public  final CharSequence getPassword(){
        return this.password;
    }

    public  final CharSequence getPhone(){
        return this.phone;
    }
}
