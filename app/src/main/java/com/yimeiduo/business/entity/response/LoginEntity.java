package com.yimeiduo.business.entity.response;

import com.yimeiduo.business.base.BaseData;

import java.io.Serializable;

public class LoginEntity extends BaseData implements Serializable {

    private String token;
    private String msg;
    private String systemTime;
    private int code;
    private int total;
    private UserInfoBean data;

    @Override
    public String toString() {
        return "LoginEntity{" +
                "token='" + token + '\'' +
                ", msg='" + msg + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", code=" + code +
                ", total=" + total +
                ", data=" + data +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(String systemTime) {
        this.systemTime = systemTime;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public UserInfoBean getData() {
        return data;
    }

    public void setData(UserInfoBean data) {
        this.data = data;
    }

    @Override
    public String dataToString() {
        return null;
    }

    public static class UserInfoBean implements Serializable {
         /*"id": "d0f79eb71e2f11eaa6a090b11c09cb64",
         "createTime": null,
         "name": "小米",
         "phone": "17666104369",
         "verifyCode": null,
         "password": "4QrcOUm6Wau+VuBX8g+IPg==",
         "role": null,
         "parentUserId": null,
         "company": null,
         "accountBalance": null,
         "personCredit": null,
         "personGive": null,
         "isOpen": null,
         "workPlaceId": null,
         "workPlaceName": null,
         "remark": null,
         "remark1": null,
         "remark2": null*/

        private String id;
        private String name;
        private String phone;
        private String password;
        private String company;
        private String workPlaceId;
        private String workPlaceName;

        private Integer accountBalance;
        private Integer ticket;
        private Integer personCredit;
        private Integer personGive;
        private Integer role;
        private Integer isUseWallet;

        public Integer getIsUseWallet() {
            return isUseWallet;
        }

        public void setIsUseWallet(Integer isUseWallet) {
            this.isUseWallet = isUseWallet;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getPersonGive() {
            return personGive;
        }

        public void setPersonGive(Integer personGive) {
            this.personGive = personGive;
        }

        public Integer getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(Integer accountBalance) {
            this.accountBalance = accountBalance;
        }

        public Integer getTicket() {
            return ticket;
        }

        public void setTicket(Integer ticket) {
            this.ticket = ticket;
        }

        public Integer getPersonCredit() {
            return personCredit;
        }

        public void setPersonCredit(Integer personCredit) {
            this.personCredit = personCredit;
        }

        public Integer getRole() {
            return role;
        }

        public void setRole(Integer role) {
            this.role = role;
        }

        public String getWorkPlaceId() {
            return workPlaceId;
        }

        public void setWorkPlaceId(String workPlaceId) {
            this.workPlaceId = workPlaceId;
        }

        public String getWorkPlaceName() {
            return workPlaceName;
        }

        public void setWorkPlaceName(String workPlaceName) {
            this.workPlaceName = workPlaceName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        @Override
        public String toString() {
            return "UserInfoBean{" +
                    "name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", password='" + password + '\'' +
                    ", company='" + company + '\'' +
                    ", workPlaceId='" + workPlaceId + '\'' +
                    ", workPlaceName='" + workPlaceName + '\'' +
                    ", accountBalance=" + accountBalance +
                    ", ticket=" + ticket +
                    ", personCredit=" + personCredit +
                    ", personGive=" + personGive +
                    ", role=" + role +
                    '}';
        }
    }

}
