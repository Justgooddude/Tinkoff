package edu.project3;

import java.net.InetAddress;
import java.net.URI;
import java.time.OffsetDateTime;

public record NginxBody (
    InetAddress remoteaddress,
    String remoteUser,
    OffsetDateTime timeLocal,
    String request,
    int status,
    long bodyBytesSent,
    URI httpReferer,
    String httpUserAgent
    ){
}
