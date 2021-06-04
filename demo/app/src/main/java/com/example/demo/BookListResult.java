package com.example.demo;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class BookListResult {

    /**
     * status : 1
     * data : [{"bookname":"幻兽少年","bookfile":"http://www.imooc.com/data/teacher/down/幻兽少年.txt"},{"bookname":"魔界的女�?,"bookfile":"http://www.imooc.com/data/teacher/down/魔界的女�?txt"},{"bookname":"盘龙","bookfile":"http://www.imooc.com/data/teacher/down/盘龙.txt"},{"bookname":"庆余�?,"bookfile":"http://www.imooc.com/data/teacher/down/庆余�?txt"},{"bookname":"武神空间","bookfile":"http://www.imooc.com/data/teacher/down/武神空间.txt"}]
     * msg : 成功
     */

    @SerializedName("status")
    private int mStatus;
    @SerializedName("msg")
    private String mMessage;
    /**
     * bookname : 幻兽少年
     * bookfile : http://www.imooc.com/data/teacher/down/幻兽少年.txt
     */

    @SerializedName("data")
    private List<Book> mData;

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<Book> getData() {
        return mData;
    }

    public void setData(List<Book> data) {
        mData = data;
    }

    public static class Book {
        @SerializedName("bookname")
        private String mBookname;
        @SerializedName("bookfile")
        private String mBookfile;

        public String getBookname() {
            return mBookname;
        }

        public void setBookname(String bookname) {
            mBookname = bookname;
        }

        public String getBookfile() {
            return mBookfile;
        }

        public void setBookfile(String bookfile) {
            mBookfile = bookfile;
        }
    }
}
