package com.asgatech.testandroidencrypt;

//public class SignatureModel {
//}

/// message : "Created"

public class SignatureModel {
    TicketModel ticket;
    String signature;

    SignatureModel(TicketModel _ticket,String _signature){
        ticket = _ticket;
        signature = _signature;
    }


}
