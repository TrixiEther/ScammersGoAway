package com.example.scammersgoaway.items;

public class RuleItem {

    private String _name;
    private String _type;
    private String _value;
    private boolean _active;

    public RuleItem(String n, String t, String v, boolean a) {
        _name = n;
        _type = t;
        _value = v;
        _active = a;
    }

    public String GetName() {
        return _name;
    }

    public void SetName(String _name) {
        this._name = _name;
    }

    public String GetType() {
        return _type;
    }

    public void SetType(String _type) {
        this._type = _type;
    }

    public String GetValue() {
        return _value;
    }

    public void SetValue(String _value) {
        this._value = _value;
    }

    public boolean IsActive() {
        return _active;
    }

    public void SetActive(boolean _active) {
        this._active = _active;
    }

}
