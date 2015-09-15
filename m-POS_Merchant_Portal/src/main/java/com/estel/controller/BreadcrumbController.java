package com.estel.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: estel
 * Date: 11/12/13
 * Time: 6:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class BreadcrumbController {

    @Autowired
    MessageSource messageSource;

    private String displayParentName ;
    private String displayChildName ;
    private String mainPanel ;

    public String getDisplayParentName() {
        return displayParentName;
    }

    public String getDisplayChildName() {
        return displayChildName;
    }

    public String getMainPanel() {
        return mainPanel;
    }

    public void headerBreadCrums(HttpServletRequest request, HttpServletResponse response) {

        String parentName = "";
        String childName = "";

        String completeUrl = request.getRequestURL().toString();
        int indexOfAdmin = completeUrl.indexOf("/admin/");
        String childLink = "";
        int indexOfChild = 0;
        int indexOfParent = 0;

        if (indexOfAdmin > 0) {
                mainPanel = messageSource.getMessage("header.main.administration",null,"default-header.main.administration",null);
                childLink = completeUrl.substring(indexOfAdmin + ("/admin/".length()), completeUrl.length());

            indexOfChild = childLink.indexOf("_");

            if (childLink.equalsIgnoreCase("merchant_bank_add.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("merchant.message.add.account",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_bank_edit.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("header.editaccount",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_bank_view.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("header.viewaccount",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_business_info_form.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("left.BusinessInformation",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_business_info_edit.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("left.businfoedit",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_business_info_view.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("left.businfoview",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_device.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("left.RegisterDevice",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_device_add.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("merchant.message.add.device",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_device_edit.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("header.devicedit",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_device_view.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("header.deviceview",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_profile_form.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("left.MerchantProfile",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_profile_view.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("header.profileview",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_profile_edit.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("header.profiledit",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_change_mpin_form.jsp")) {
                displayParentName = messageSource.getMessage("left.MyAccount",null,"default",null);
                displayChildName = messageSource.getMessage("merchant.message.change.mpin",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_change_mpin_success.jsp")) {
                displayParentName = messageSource.getMessage("left.MyAccount",null,"default",null);
                displayChildName = messageSource.getMessage("merchant.message.change.mpin",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_change_password_form.jsp")) {
                displayParentName = messageSource.getMessage("left.MyAccount",null,"default",null);
                displayChildName = messageSource.getMessage("left.changePassword",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_change_password_success.jsp")) {
                displayParentName = messageSource.getMessage("left.MyAccount",null,"default",null);
                displayChildName = messageSource.getMessage("left.changePassword",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_change_password_fail.jsp")) {
                displayParentName = messageSource.getMessage("left.MyAccount",null,"default",null);
                displayChildName = messageSource.getMessage("left.changePassword",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_forgot_pin_form.jsp")) {
                displayParentName = messageSource.getMessage("left.MyAccount",null,"default",null);
                displayChildName = messageSource.getMessage("left.ForgotMPin",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_transaction_history.jsp")) {
                displayParentName = messageSource.getMessage("left.Reports",null,"default",null);
                displayChildName = messageSource.getMessage("left.transactionhistory",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_transaction_summary.jsp")) {
                displayParentName = messageSource.getMessage("left.Reports",null,"default",null);
                displayChildName = messageSource.getMessage("left.transactionsummary",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_batch_summary.jsp")) {
                displayParentName = messageSource.getMessage("left.Reports",null,"default",null);
                displayChildName = messageSource.getMessage("left.batchhistory",null,"default",null);
            } else if (childLink.equalsIgnoreCase("admin_merchant_user_list.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("left.Merchant",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_order_device.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("left.order.card.reader",null,"default",null);
            } else if (childLink.equalsIgnoreCase("merchant_receipt.jsp")) {
                displayParentName = messageSource.getMessage("left.MPOS",null,"default",null);
                displayChildName = messageSource.getMessage("left.view.receipt",null,"default",null);
            }
            else if (childLink.equalsIgnoreCase("admin_batch_history.jsp")) {
                displayParentName = messageSource.getMessage("left.Report",null,"default",null);
                displayChildName = messageSource.getMessage("batch.history",null,"default",null);
            }
            else if (childLink.equalsIgnoreCase("admin_trans_details.jsp")) {
                displayParentName = messageSource.getMessage("left.Report",null,"default",null);
                displayChildName = messageSource.getMessage("transaction.detail",null,"default",null);
            }
            else if (childLink.equalsIgnoreCase("admin_trans_summary.jsp")) {
                displayParentName = messageSource.getMessage("left.Report",null,"default",null);
                displayChildName = messageSource.getMessage("transaction.summary",null,"default",null);
            }
            else if (indexOfChild < 0) {
                indexOfParent = childLink.indexOf(".");
                parentName = childLink.substring(0, indexOfParent);
                displayParentName = parentName.substring(0, 1).toUpperCase() + parentName.substring(1, parentName.length());
                displayChildName = "View " + displayParentName;
            }else {
                indexOfParent = childLink.indexOf(".");
                parentName = childLink.substring(0, indexOfChild);
                displayParentName = parentName.substring(0, 1).toUpperCase() + parentName
                        .substring(1, parentName.length());
                childName = childLink.substring(indexOfChild + 1, indexOfParent);
                childName = childName + " " + displayParentName;
                displayChildName = childName.substring(0, 1).toUpperCase() + childName.substring(1, childName.length());
            }
        }
    }
}
