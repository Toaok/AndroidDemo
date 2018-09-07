package com.leaks.core;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

/**
 #############################################################
 ###################################################   #######
 ###############################################   /~\   #####
 ############################################   _- `~~~', ####
 ##########################################  _-~       )  ####
 #######################################  _-~          |  ####
 ####################################  _-~            ;  #####
 ##########################  __---___-~              |   #####
 #######################   _~   ,,                  ;  `,,  ##
 #####################  _-~    ;'                  |  ,'  ; ##
 ###################  _~      '                    `~'   ; ###
 ############   __---;                                 ,' ####
 ########   __~~  ___                                ,' ######
 #####  _-~~   -~~ _                               ,' ########
 ##### `-_         _                              ; ##########
 #######  ~~----~~~   ;                          ; ###########
 #########  /          ;                        ; ############
 #######  /             ;                      ; #############
 #####  /                `                    ; ##############
 ###  /                                      ; ###############
 #                                            ################
 */
public class Leaks {

    public static void init(Context context) {
        if (LeakCanary.isInAnalyzerProcess(context.getApplicationContext())) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install((Application) context.getApplicationContext());
    }
}
