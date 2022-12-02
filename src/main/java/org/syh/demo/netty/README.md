# Netty Notes

## ChannelOption
### ChannelOption.TCP_NODELAY
In Netty, the ChannelOption.TCP_NODELAY option is used to enable or disable the Nagle algorithm for TCP connections. The Nagle algorithm is a mechanism used in TCP to reduce the number of small packets that are sent over the network. It works by buffering small packets of data until there is enough to fill a single network packet, or until a certain amount of time has passed.

### ChannelOption.SO_BACKLOG
In Netty, the ChannelOption.SO_BACKLOG option is used to specify the maximum number of pending connections that can be queued up by the operating system when the server socket is accepting connections. This option is only applicable to server sockets, and has no effect on client sockets.

### ChannelOption.SO_KEEPALIVE
In Netty, the ChannelOption.SO_KEEPALIVE option is used to enable or disable the TCP keep-alive mechanism. When this option is enabled, the operating system will periodically send a small packet to the remote peer to check if the connection is still alive. This can be useful for detecting and recovering from broken connections.