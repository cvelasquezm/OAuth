# OAuth
PoC for OAuth 2.0

This is a proof of concept for OAuth 2.0, it was implemented with Java and made on way web services (Authorization Services and Resources Services)

This component has a couple of endpoints, the first one for Authorization Services

# {server}/auth_server/v1/getToken

With headers

# clientId
# project
# endPoint
# xTimestamp

The second one is Resources Services, this endpoint use the token got in the Authorization Service

# {server}/resource_server/v1/getResource

With headers

# Authorization
