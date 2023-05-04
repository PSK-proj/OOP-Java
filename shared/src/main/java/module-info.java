module shared {
  exports org.psk.shared;
  opens org.psk.shared to client, server;
  exports org.psk.shared.util;
  opens org.psk.shared.util to client, server;
}