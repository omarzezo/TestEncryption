package com.asgatech.testandroidencrypt;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.asgatech.testandroidencrypt.services.CommonService;
import com.asgatech.testandroidencrypt.services.ECDSAService;
import com.asgatech.testandroidencrypt.services.ECIESService;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    String otu;
    String sessionKey;
    String signature;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            prepareData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   @RequiresApi(api = Build.VERSION_CODES.N)
   void prepareData() throws Exception {
       otu= CommonService.generateRandomKey(7);
       sessionKey= CommonService.generateRandomKey(10);
       TicketModel ticketModel =new TicketModel(otu,sessionKey);
       Gson gson = new Gson();
       String jsonTicket = gson.toJson(ticketModel);
       Log.e("jsonTicket>>",jsonTicket.toString());
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//           signature= ECDSAService.sign("",CommonService.getClientPrivateKey());
           signature="MEUCIQCYCwImSLI8D8FYBX+bfmLSsSg8tPtkd6Gt2spoqTVbkgIgM5YJSGXVwBX+gUZ9rtoBLUsLLlqgxUzGtdrVMTdxlOM=";
           SignatureModel signatureModel =new SignatureModel(ticketModel,signature);
           String jsonSignature = gson.toJson(signatureModel);
           Log.e("jsonSignature>>",jsonSignature.toString());
           makeEncrypt(jsonSignature);

       }
   }

   @RequiresApi(api = Build.VERSION_CODES.O)
   void makeEncrypt(String json ) throws Exception {
//       ECIESService.encrypt(json,CommonService.getServerPublicKey(),CommonService.getClientPrivateKey());
       ECIESService.encrypt(json,"MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEnwWOUXfFA+fREhmzEzsB9wjx79yz2DpTLnhw3W0kjfy4jwE27V5fiCFs3cb6gXWfVtjqyRr2U9WBqTsx9+DTwA==");
   }
}