package com.estel.controller;

import com.estel.dto.BankModel;
import com.estel.entity.*;
import com.estel.service.*;
import com.estel.utility.Constant;
import com.estel.utility.EncryptionAlgorithm;
import com.estel.utility.EncryptionAlgorithmImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: estel
 * Date: 3/12/13
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class BankController {

    @Autowired
    BankService bankService;

    @Autowired
    ContactdetailService contactdetailService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    BusinessService businessService;

    @Autowired
    BankAccountTypeService bankAccountTypeService;

    @Autowired
    UserService userService;

    @Autowired
    StatusService statusService;

    @Autowired
    PinService pinService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    EmailService emailService;

    @Autowired
    SmsService smsService;

    @Autowired
    AudittrailService audittrailService;

    @RequestMapping("/bankAccountList")
    public String bankAccountList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,@ModelAttribute("bankModel") BankModel bankModel, BindingResult result) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        ///////Added for sequence registration

        Contactdetail residentialAddress = null;
        Contactdetail permanentAddress = null;
        try {
            residentialAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "1");

            permanentAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "2");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if ((residentialAddress != null || permanentAddress != null) && (residentialAddress.getContactdetailsContactName() == null)) {
            session.setAttribute("sequence_info_msg", messageSource.getMessage("sequence.profile.msg", null, "default-sequence.profile.msg", null));
            session.setAttribute("stepNo", "2");
            return "redirect:/merchantProfileAction.do";
        }

        Business business = businessService.getBusinessByAgentId(agentId);

        if (business == null) {

            session.setAttribute("sequence_info_msg", messageSource.getMessage("sequence.business.info.msg", null, "default-sequence.business.info.msg", null));
            session.setAttribute("stepNo", "1");
            return "redirect:/merchantBusinessInfoAction.do";
        }
        //////

        //BankModel bankModel = new BankModel();

        bankModel.setAccountTypeList(bankAccountTypeService.listBankAccountTypes());

        List<Bank> bankList = bankService.listBankByAgentId(agentId);

        if (bankList == null || bankList.isEmpty()) {

            bankModel.setBankList(bankList);
            map.put("bankModel", bankModel);
            return "merchant_bank_add";

        } else {
            bankModel.setBankId(bankList.get(0).getBankId());
            bankModel.setAccount_name(bankList.get(0).getBankAccountName());
            bankModel.setAccount_type(bankAccountTypeService.getBankAccountTypeById(bankList.get(0).getBankAccountTypeId()).getBankAccountTypeName());
            bankModel.setBank_name(bankList.get(0).getBankName());
            bankModel.setBranch_name(bankList.get(0).getBankBranchName());
            bankModel.setBranch_code(bankList.get(0).getBankBranchCode());
            bankModel.setRouting_number(bankList.get(0).getBankRoutingNo());
            bankModel.setAccount_num1(bankList.get(0).getBankAccountNumber());

            bankModel.setBankList(bankList);
            map.put("bankModel", bankModel);
            return "merchant_bank_view";
        }
    }


    @RequestMapping(value = "/bankAccountEdit", method = RequestMethod.POST)
    public String bankAccountEdit(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                                  @ModelAttribute("bankModel") BankModel bankModel, BindingResult result) {


        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";


        if (request.getParameter("pageAction") != null && request.getParameter("pageAction").toString().trim().equalsIgnoreCase("view")) {


            if (request.getParameter("bankId") != null && !request.getParameter("bankId").trim().equalsIgnoreCase("")) {

                Bank bank = bankService.getBankById(Long.parseLong(request.getParameter("bankId")));

                bankModel.setAccount_name(bank.getBankAccountName());
                bankModel.setAccount_type(bankAccountTypeService.getBankAccountTypeById(bank.getBankAccountTypeId()).getBankAccountTypeName());
                bankModel.setBank_name(bank.getBankName());
                bankModel.setBranch_name(bank.getBankBranchName());
                bankModel.setBranch_code(bank.getBankBranchCode());
                bankModel.setRouting_number(bank.getBankRoutingNo());
                bankModel.setAccount_num1(bank.getBankAccountNumber());

                bankModel.setAccountTypeList(bankAccountTypeService.listBankAccountTypes());
            }

            map.put("bankModel",bankModel);
            return "merchant_bank_view";


        } else if (request.getParameter("pageAction") != null && request.getParameter("pageAction").toString().trim().equalsIgnoreCase("edit")) {


            //if (request.getParameter("bankId") != null && !request.getParameter("bankId").trim().equalsIgnoreCase("")) {

               // Bank bank = bankService.getBankById(Long.parseLong(request.getParameter("bankId")));
            //bankModel.setBankId(Long.parseLong(request.getParameter("bankId")));

                Bank bank = bankService.listBankByAgentId(agentId).get(0);
                bankModel.setBankId(bank.getBankId());
                bankModel.setAccount_name(bank.getBankAccountName());
                bankModel.setAccount_type(bankAccountTypeService.getBankAccountTypeById(bank.getBankAccountTypeId()).getBankAccountTypeName());
                bankModel.setBank_name(bank.getBankName());
                bankModel.setBranch_name(bank.getBankBranchName());
                bankModel.setBranch_code(bank.getBankBranchCode());
                bankModel.setRouting_number(bank.getBankRoutingNo());
                bankModel.setAccount_num1(bank.getBankAccountNumber());

                bankModel.setAccountTypeList(bankAccountTypeService.listBankAccountTypes());
            //}
            return "merchant_bank_edit";
        }
        //  this block will called for updation in db
        else {

            bankModel.setBankList(bankService.listBanks());
            //check if pin is valid or not..if not valid show error on merchant_bank_edit jsp page
            //String md5Pin = MD5.MD5Convertor((merchantService.getMerchantById(agentId).getMerchantCode()) + bankModel.getPin()).toUpperCase();
            EncryptionAlgorithm encryptionAlgorithm = new EncryptionAlgorithmImpl(Constant.SHA2);
            String md5Pin = encryptionAlgorithm.encrypt((merchantService.getMerchantById(agentId).getMerchantCode()) + bankModel.getPin());
            Pin pin = pinService.verifyPinByPinTypeAndAgentId(md5Pin, Constant.IPIN, agentId);
            if (pin != null) {

                Bank bank = bankService.getBankById(Long.parseLong(request.getParameter("bankId")));

                String old_val = "Account Name="+bank.getBankAccountName()+
                        ",Account Type="+bankAccountTypeService.getBankAccountTypeById(bank.getBankAccountTypeId()).getBankAccountTypeName()+
                        ",Bank Name="+bank.getBankName()+
                        ",Branch Name="+bank.getBankBranchName()+
                        ",Branch Code="+bank.getBankBranchCode()+
                        ",Routing number="+bank.getBankRoutingNo()+
                        ",Account Number="+bank.getBankAccountNumber();


                BankAccountType bankAccountType = bankAccountTypeService.getBankAccountTypeByAccountTypeName(bankModel.getAccount_type());

                bank.setBankMerchantId(agentId);
                bank.setBankAccountTypeId(bankAccountType.getBankAccountTypeId());
                bank.setBankAccountNumber(bankModel.getAccount_num1());
                bank.setBankAccountName(bankModel.getAccount_name());
                bank.setBankName(bankModel.getBank_name());
                bank.setBankBranchName(bankModel.getBranch_name());
                bank.setBankRoutingNo(bankModel.getRouting_number());
                bank.setBankBranchCode(bankModel.getBranch_code());

                bankService.updateBank(bank);

                bankModel.setAccount_type(bankAccountType.getBankAccountTypeName());

                session.setAttribute("bank_msg", messageSource.getMessage("bank.update.sucess", null, "default-bank.update.sucess", null));

                //Send Email
                User user = userService.getUserByAgentId(agentId);
                try {
                    emailService.sendEmail(user.getUserMailId(), messageSource.getMessage("BANK-EDIT",null,"default-BANK-EDIT",null),"BANK-EDIT", new Object[]{user.getUserFirstName()});
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Send Sms
                try {
                    smsService.sendSms("BANK_ACCOUNT_EDIT", contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "1").getContactdetailsMobileNumber(), new Object[]{user.getUserFirstName()});
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String ipAddress = (request).getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = (request).getRemoteAddr();
                }



                String new_val = "Account Name="+bankModel.getAccount_name()+
                        ",Account Type="+bankModel.getAccount_type()+
                        ",Bank Name="+bankModel.getBank_name()+
                        ",Branch Name="+bankModel.getBranch_name()+
                        ",Branch Code="+bankModel.getBranch_code()+
                        ",Routing number="+bankModel.getRouting_number()+
                        ",Account Number="+bankModel.getAccount_num1();

                audittrailService.addAudittrailByUserId(userService.getUserByAgentId(agentId).getUserId(), "Update Bank Details", "Update", ipAddress, "Bank Details Update", old_val, new_val);


                map.put("bankModel", bankModel);
                return "merchant_bank_view";

            } else {

                bankModel.setAccountTypeList(bankAccountTypeService.listBankAccountTypes());
                bankModel.setPin("");

                session.setAttribute("edit_bank_err_msg", messageSource.getMessage("user.infomsg.pin", null, "default-user.infomsg.pin", null));
                map.put("bankModel", bankModel);
                return "merchant_bank_edit";
            }

        }
    }


    @RequestMapping(value = "/bankAccountAdd", method = RequestMethod.POST)
    public String bankAccountAdd(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                                 @ModelAttribute("bankModel") BankModel bankModel, BindingResult result) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        //check if pin is valid or not..if not valid show error on merchant_bank_edit jsp page
        //String md5Pin = MD5.MD5Convertor((merchantService.getMerchantById(agentId).getMerchantCode()) + bankModel.getPin()).toUpperCase();
        EncryptionAlgorithm encryptionAlgorithm = new EncryptionAlgorithmImpl(Constant.SHA2);
        String md5Pin = encryptionAlgorithm.encrypt((merchantService.getMerchantById(agentId).getMerchantCode()) + bankModel.getPin());
        Pin pin = pinService.verifyPinByPinTypeAndAgentId(md5Pin, Constant.IPIN, agentId);
        if (pin != null) {

            bankService.addBankByAgentId(bankModel.getAccount_num1(),
                    bankModel.getAccount_type(),
                    agentId,
                    "Y",
                    "MPOS",
                    bankModel.getBranch_code(),
                    bankModel.getAccount_name(),
                    bankModel.getBank_name(),
                    bankModel.getBranch_name(),
                    bankModel.getRouting_number());


            session.setAttribute("bank_msg", messageSource.getMessage("bank.add.sucess", null, "default-bank.add.sucess", null));
            bankModel.setBankList(bankService.listBankByAgentId(agentId));

            //Send Email
            User user = userService.getUserByAgentId(agentId);
            try {
                emailService.sendEmail(user.getUserMailId(), messageSource.getMessage("BANK-ADD",null,"default-BANK-ADD",null),"BANK-ADD",new Object[]{user.getUserFirstName()});
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Send Sms
            try {
                smsService.sendSms("BANK_ACCOUNT_ADD", contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "1").getContactdetailsMobileNumber(), new Object[]{user.getUserFirstName()});
            } catch (Exception e) {
                e.printStackTrace();
            }

            String ipAddress = (request).getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = (request).getRemoteAddr();
            }

            String old_val = null;
            String new_val = "Account Name="+bankModel.getAccount_name()+
                    ",Account Type="+bankModel.getAccount_type()+
                    ",Bank Name="+bankModel.getBank_name()+
                    ",Branch Name="+bankModel.getBranch_name()+
                    ",Branch Code="+bankModel.getBranch_code()+
                    ",Routing number="+bankModel.getRouting_number()+
                    ",Account Number="+bankModel.getAccount_num1();

            audittrailService.addAudittrailByUserId(userService.getUserByAgentId(agentId).getUserId(), "Add Bank Details", "Add", ipAddress, "Bank Details Added", old_val, new_val);


            return "merchant_bank_view";


        } else {

           bankModel.setAccountTypeList(bankAccountTypeService.listBankAccountTypes());
           /* bankModel.setPin("");
            bankModel.setBankList(bankService.listBankByAgentId(agentId));*/

            session.setAttribute("add_bank_err_msg", messageSource.getMessage("user.infomsg.pin", null, "default-user.infomsg.pin", null));
          //  return "redirect:/bankAccountList.do";
            return "merchant_bank_add";

        }
    }
}
