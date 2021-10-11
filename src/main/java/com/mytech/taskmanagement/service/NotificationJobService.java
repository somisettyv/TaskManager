package com.mytech.taskmanagement.service;

import java.io.IOException;

public interface NotificationJobService {

   void sendRemainders() throws IOException;
   void processRequest();
}
