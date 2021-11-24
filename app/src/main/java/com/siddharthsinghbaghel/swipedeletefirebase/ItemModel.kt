package com.siddharthsinghbaghel.swipedeletefirebase

public class ItemModel {
    var title: String? = null
    var description: String? = null

    constructor() {}
    constructor(title: String?, desc: String?) {
        this.title = title
        this.description = desc
    }
}