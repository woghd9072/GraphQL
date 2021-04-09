<template>
    <div class="home">
        <button @click="sendQuery">send query</button>

        <pre>{{ books }}</pre>
    </div>
</template>

<script>
    // @ is an alias to /src
    import {ApolloClient} from "apollo-client"
    import {InMemoryCache} from "apollo-cache-inmemory"
    import {createHttpLink} from "apollo-link-http"
    import gql from "graphql-tag"

    const client = new ApolloClient({
        link: createHttpLink({uri: "http://localhost:8080/graphql"}),
        cache: new InMemoryCache()
    })

    export default {
        name: 'Home',
        data: () => ({
            books: [
                {
                    id: "",
                    name: "",
                    pageCount: "",
                    author: {
                        firstName: "",
                        lastName: ""
                    }
                }
            ]
        }),
        methods: {
            sendQuery() {
                client.query({
                    query: gql`query {
                        books {
                            id
                            name
                            pageCount
                            author {
                                firstName
                                lastName
                            }
                        }
                    }`
                }).then(({data}) => {
                    console.log(data)
                    this.books = data.books
                })
            }
        }
    }
</script>
