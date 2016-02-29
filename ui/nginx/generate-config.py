import os
from os.path import abspath, dirname, join, isfile
from jinja2 import Template

ROOT_DIR = abspath(dirname(__file__))
template_file = os.path.join(ROOT_DIR, "nginx.conf.template")
template = Template(open(template_file).read())
print template.render(os.environ)
