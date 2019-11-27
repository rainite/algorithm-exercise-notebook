# Tutorial for setting up an OpenVPN server and client in DAL

## Preparation

First before we want to set up an OpenVPN server, let's go through the knowledge we need for this tool.
Understand what is TLS/SSL

OpenVPN connection is besed on TLS technology,  we can refer to this page: https://en.wikipedia.org/wiki/Transport_Layer_Security to have a basic idea of what is TLS. Basically speaking, TLS need a pair of public key and private key to encrypt messages between clients and servers.  We can use the equation below for better describing:

Receiving:

    Server: original message + server_private_key = [encrypted message]
    Client: [encrypted message] + server_public_key = original message

Sending:

    Client: original message + server_public_key = [encrypted message]
    Server: [encrypted message] + server_private_key = original message

## How do we know the public key can be trusted?

The public key can be faked, so we need something to prove the public key is exactly what we want. That's why We need CA to sign the public key. We can refer to this page to know more about what is CA: https://en.wikipedia.org/wiki/Certificate_authority
How do I generate all of the key pairs and sign them?

The luckiest thing is, OpenVPN's official site has a very detailed tutorial to show you how to generate everything we need. Take a look at this page:  https://openvpn.net/community-resources/setting-up-your-own-certificate-authority-ca

According to the tutorial, we need a tool called easy-rsa (https://github.com/OpenVPN/easy-rsa-old) to finish the generate. Below is the process that abstracted from the tool to show how it works:

    Generate CA Private Key (CA.key)
    Generate CA Public Key (CA.crt)
    Generate server Private Key (server.crt)
    Generate server Certificate Signing Request (server.csr)
    server.csr + CA.key => server public key (server.crt)

If we just want to use username and password for clients to connect, that's enough. If we want to use certificate files to connect, which means doubly authentication, we also need to generate clients key pairs:

    Generate client1 Private Key (client1.key)
    Generate client1 Certificate Signing Request (client1.csr)
    client1.csr + CA.key => client1 public key (client1.crt)
    Repeat 1-3 to generate and sign multiple clients public keys

Note that all of these process above is only for TLS hand shake, after hand shake successfully, we will change to use Symmetric-key algorithm for the connection, so we also have to generate Diffie–Hellman parameters to do this job.

## Set up the server side

To make it simple, we need these files in order to set up the OpenVPN server:

    A Certificate Authority public key (CA.crt)
    Server side key pair
    Diffie Hellman parameters
    local usernames/password for clients

## Set up the client side

Quiz: After understanding the basic idea of TLS, can you come up with what kind of files that a client need?

Answer: It depends! But at least, we need the CA public key to check if the server public key is valid or not. Just like I mentioned earlier in the article, if we want to use a double authentication way we will need client public key(signed by CA private key) and the client private key.

### Windows/iOS/Android

We have to use an ovpn file to describe the information that a client need:

    client
    dev tun
    proto udp
    remote 10.45.1.113 1194
    resolv-retry infinite
    nobind
    persist-key
    persist-tun
    remote-cert-tls server
    cipher AES-256-CBC
    verb 3
    auth-user-pass
    <ca>
    ................[CA public key]...............
    </ca>

Remote is the ip address and port of the server, and change your ca public key in the tag \<ca>.

Then the connection should be enabled.