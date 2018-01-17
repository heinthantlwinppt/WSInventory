package com.ppt.wsinventory.common;

/**
 * Created by User on 02/01/2018.
 */

public class WsEvents {

    public static final String OPEN_RECEIVING_INVENTORY = "Receiving";
    public static final String OPEN_TAGGING_INVENTORY = "Tagging";

    public static class EventNewChange {
        private String mvalue;
        private String msolution_name;
        private String mactionname;

        public String getActionname() {
            return mactionname;
        }

        public String getSolution_name() {
            return msolution_name;
        }

        public EventNewChange(String value, String solution_name, String actionname) {
            this.mvalue = value;
            this.msolution_name = solution_name;

            this.mactionname = actionname;

        }

        public String getValue() {
            return mvalue;
        }

    }

    public static class EventInputChange {
        private String mvalue;
        private String msolution_name;
        private String mactionname;

        public String getActionname() {
            return mactionname;
        }

        public String getSolution_name() {
            return msolution_name;
        }

        public EventInputChange(String goodsid, String solution_name, String actionname) {
            this.mvalue = goodsid;
            this.msolution_name = solution_name;

            this.mactionname = actionname;

        }

        public String getGoodsid() {
            return mvalue;
        }

    }



    public static class EventOpenScreen {
        private String actionname;
        public EventOpenScreen(String actionname) {
            this.actionname = actionname;
        }
        public String getActionname() {
            return actionname;
        }
    }

    public static class EventOpenSmithJob {
        private String joborder_no;
        public EventOpenSmithJob(String joborder_no) {
            this.joborder_no = joborder_no;
        }

        public String getJoborder_no() {
            return joborder_no;
        }
    }


    public static class EventMessages {

        private int mResult;
        private String mAction;

        public EventMessages(String Action, int MsgResult) {
            this.mResult = MsgResult;
            this.mAction = Action;
        }

        public int getMsgResult() {
            return mResult;
        }

        public String getAction() {
            return mAction;
        }
    }

    public static class EventShowMessage {
        String title;
        String caption;
        String action;
        String ButtonLeft;
        String ButtonRight;

        public EventShowMessage(String title,
                                String caption,
                                String action,
                                String buttonLeft,
                                String buttonRight) {
            this.title = title;
            this.caption = caption;
            this.action = action;
            this.ButtonLeft = buttonLeft;
            this.ButtonRight = buttonRight;
        }

        public String getTitle() {
            return title;
        }

        public String getCaption() {
            return caption;
        }

        public String getAction() {
            return action;
        }

        public String getButtonLeft() {
            return ButtonLeft;
        }

        public String getButtonRight() {
            return ButtonRight;
        }
    }

}
