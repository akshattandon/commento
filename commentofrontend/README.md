# codetestfrontend

## Development

### PreRequisites

Node.js
Yarn

### Install Dependencies

```
npm install
or
yarn
```

We use npm scripts and [Webpack][] as our build system.

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

```
npm start
or
yarn start
```

## Building for production

Then navigate to [http://localhost:9000](http://localhost:9000) in your browser.

Refer to [Using JHipster in production][] for more details.

## Testing ( In Progress )

### Client tests

Unit tests are run by [Jest][] and written with [Jasmine][]. They're located in [src/test/javascript/](src/test/javascript/) and can be run with:

```
npm test
```

For more information, refer to the [Running tests page][].

### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

## Docker ( In Progress)
