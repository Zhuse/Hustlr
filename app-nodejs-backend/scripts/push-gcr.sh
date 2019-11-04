#!/bin/bash

# Get the directory of the script so we know where we are
# Allows us to run the script from anywhere

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd "$DIR"
cd ..

echo "Building image"
docker tag $(docker build -q .) gcr.io/cpen321-hustlr/hustlr-node
echo "Pushing to GCR"
docker push gcr.io/cpen321-hustlr/hustlr-node
