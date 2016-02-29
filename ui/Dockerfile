FROM library/nginx
RUN apt-get update -q && \
    apt-get upgrade -qy && \
    DEBIAN_FRONTEND=noninteractive apt-get install -qy python-pip && \
    pip install jinja2

WORKDIR /hello-admiral

ADD ui /hello-admiral

CMD ["/hello-admiral/nginx/start-nginx.sh"]
