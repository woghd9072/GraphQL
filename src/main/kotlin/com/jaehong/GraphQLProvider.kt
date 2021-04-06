package com.jaehong

import com.google.common.base.Charsets
import com.google.common.io.Resources
import graphql.GraphQL
import graphql.schema.GraphQLSchema
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeDefinitionRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import java.io.IOException
import java.net.URL
import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring
import kotlin.jvm.Throws


@Component
class GraphQLProvider {

    @Autowired
    var graphQLDataFetchers: GraphQLDataFetchers? = null

    private var graphQL: GraphQL? = null

    @PostConstruct
    @Throws(IOException::class)
    fun init() {
        val url: URL = Resources.getResource("schema.graphqls")
        val sdl: String = Resources.toString(url, Charsets.UTF_8)
        val graphQLSchema = buildSchema(sdl)
        graphQL = GraphQL.newGraphQL(graphQLSchema).build()
    }

    private fun buildSchema(sdl: String): GraphQLSchema {
        val typeRegistry: TypeDefinitionRegistry = SchemaParser().parse(sdl)
        val runtimeWiring = buildWiring()
        val schemaGenerator = SchemaGenerator()
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)
    }

    private fun buildWiring(): RuntimeWiring {
        return RuntimeWiring.newRuntimeWiring()
            .type(
                newTypeWiring("Query")
                    .dataFetcher("bookById", graphQLDataFetchers!!.bookByIdDataFetcher)
                    .dataFetcher("book", graphQLDataFetchers!!.allDataFetcher)
            )
            .type(
                newTypeWiring("Book")
                    .dataFetcher("author", graphQLDataFetchers!!.authorDataFetcher)
            )
            .build()
    }

    @Bean
    fun graphQL(): GraphQL? {
        return graphQL
    }
}