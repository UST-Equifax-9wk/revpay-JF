package com.revature.revpay.dto;

public class TransactionDto{

    private Integer senderAccountId;
    private Integer receiverAccountId;
    private Double amount;
    // You can include other transaction-related fields as needed

        // Constructors
        public TransactionDto() {
        }

        public TransactionDto(Integer senderAccountId, Integer receiverAccountId, Double amount) {
            this.senderAccountId = senderAccountId;
            this.receiverAccountId = receiverAccountId;
            this.amount = amount;
        }

        // Getters and Setters
        public Integer getSenderAccountId() {
            return senderAccountId;
        }

        public void setSenderAccountId(Integer senderAccountId) {
            this.senderAccountId = senderAccountId;
        }

        public Integer getReceiverAccountId() {
            return receiverAccountId;
        }

        public void setReceiverAccountId(Integer receiverAccountId) {
            this.receiverAccountId = receiverAccountId;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        // You can include other getters and setters as needed

        // toString method for debugging purposes (optional)
        @Override
        public String toString() {
            return "TransactionDTO{" +
                   "senderAccountId=" + senderAccountId +
                   ", receiverAccountId=" + receiverAccountId +
                   ", amount=" + amount +
                   '}';
        }
    }
