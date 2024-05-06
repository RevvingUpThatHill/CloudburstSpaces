# Cloudlabs - cloudbusting
Autoscaling on-demand hosted VSCode websocket-based browser workspaces which maintain a registry with real-time performance info & workspace metrics.
## Endpoints
POST /workspace/
DELETE /workspace/
## Contributors
Ted Balashov
Richard Hawkins

docker run -d --name=workspace2 -e PUID=1000 -e PGID=1000 -e TZ=Etc/UTC -e DEFAULT_WORKSPACE=/config/workspace -p 7000:7000 -v /path/to/appdata/config:/config --restart unless-stopped lscr.io/linuxserver/code-server:latest

docker run -d --name=code-server2 -e PUID=1000 -e PGID=1000 -e TZ=Etc/UTC -e PASSWORD=password `#optional` -e HASHED_PASSWORD= `#optional` -e SUDO_PASSWORD=password `#optional` -e SUDO_PASSWORD_HASH= `#optional` -e PROXY_DOMAIN=code-server.my.domain `#optional` -e DEFAULT_WORKSPACE=/config/workspace `#optional` -p 8443:8443 -v /path/to/appdata/config:/config --restart unless-stopped lscr.io/linuxserver/code-server:latest