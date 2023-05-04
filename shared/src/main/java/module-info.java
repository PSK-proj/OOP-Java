module shared {
  exports org.psk.shared;
  opens org.psk.shared to client, server;
}