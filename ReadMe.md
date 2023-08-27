```bash
docker build -t order-items-reserve .
```

To run locally please use the following command:
```bash
docker run --rm --name reserver -p 80:80 -e AZURE_STORAGE_CONNECTION_STRING="<PLACEHOLDER FOR SA CONNECTION STRING>" order-items-reserve:latest
```