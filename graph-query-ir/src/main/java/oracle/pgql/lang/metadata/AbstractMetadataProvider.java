/*
 * Copyright (C) 2013 - 2023 Oracle and/or its affiliates. All rights reserved.
 */
package oracle.pgql.lang.metadata;

import java.util.List;
import java.util.Optional;

import oracle.pgql.lang.ir.SchemaQualifiedName;

/**
 * All method in this class return an Optional so that implementations can choose to only implement a subset and
 * incrementally provide more metadata over time.
 */
public abstract class AbstractMetadataProvider {

  /**
   * Get the schema of the default graph, including its labels, properties and property types.
   * 
   * @param graphName
   * @return the schema of the graph
   */
  public Optional<GraphSchema> getGraphSchema() {
    return Optional.empty();
  }

  /**
   * Get the schema of a graph, including its labels, properties and property types.
   * 
   * @param graphName
   *          the name of the graph with schema qualifier (schema qualifier can be null)
   * @return the schema of the graph
   */
  public Optional<GraphSchema> getGraphSchema(SchemaQualifiedName graphName) {
    return Optional.empty();
  }

  /**
   * Get the default string type, used for e.g. string literals and labels.
   * 
   * Examples:
   * 
   * - 'abc' => STRING
   * 
   * - 'abc' => VARCHAR(2000)
   * 
   * @return the string literal type.
   */
  public Optional<String> getDefaultStringType() {
    return Optional.empty();
  }

  /**
   * Get the default short integer type. For example, INTEGER or NUMBER(20)
   * 
   * Used for:
   * 
   * - Type of EXTRACT function when extracting year/month/day/hour/minute/timezone_hour/timezone_minute
   * 
   * @return the short integer type.
   */
  public Optional<String> getDefaultShortIntegerType() {
    return Optional.empty();
  }

  /**
   * Get the default long integer type. For example, LONG or NUMBER(200)
   * 
   * Used for:
   * 
   * - integer literal
   * 
   * - COUNT aggregate
   * 
   * @return the long integer type.
   */
  public Optional<String> getDefaultLongIntegerType() {
    return Optional.empty();
  }

  /**
   * Get the default decimal type. For example, DOUBLE or NUMBER(200).
   * 
   * @return the decimal type.
   */
  public Optional<String> getDefaultDecimalType() {
    return Optional.empty();
  }

  /**
   * Get the return type of a unary operation.
   * 
   * @param op
   *          e.g. NOT, - (unary minus)
   * @param type
   *          e.g. BOOLEAN
   * @return the return type of the operation (e.g. LONG) or null if the operation is not defined for the input type
   */
  public Optional<String> getOperationReturnType(UnaryOperation op, String type) {
    return Optional.empty();
  }

  /**
   * Get the return type of a binary operation.
   * 
   * @param op
   *          e.g. multiplication
   * @param typeA
   *          e.g. LONG
   * @param typeB
   *          e.g. INTEGER
   * @return the return type of the operation (e.g. LONG) or null if the operation is not defined for the two input
   *         types
   */
  public Optional<String> getOperationReturnType(BinaryOperation op, String typeA, String typeB) {
    return Optional.empty();
  }

  /**
   * Get the union type of two data types.
   * 
   * This is used for things like:
   * 
   * 1. Decide on the type of a property access when multiple vertex/edge tables have the same property but with
   * different property types and
   * 
   * 2. Decide on the type of a CASE statement when subexpressions have different data types.
   * 
   * 3. Decide on the type of IN predicate when expressions in the value list have different data types.
   * 
   * Examples:
   * 
   * - LONG, INTEGER => LONG
   * 
   * - VARCHAR(10), VARCHAR(20) => VARCHAR(20)
   * 
   * - LONG, DATE => null
   * 
   * @param packageName
   * @param functionName
   * @param argumentTypes
   * @return the union type of the two type, or null if the types are incompatible
   */
  public Optional<String> getUnionType(String typeA, String typeB) {
    return Optional.empty();
  }

  /**
   * Gets the signatures of the functions that are available in the system, including the built-in and user-defined
   * functions.
   * 
   * @return the functions available in the system
   */
  public Optional<List<FunctionSignature>> getFunctionSignatures() {
    return Optional.empty();
  }

  /**
   * Gets synonyms for data types (for example INT -> INTEGER)
   *
   * @return the list of data type synonyms of the system
   */
  public Optional<List<DataTypeSynonym>> getDataTypeSynonyms() {
    return Optional.empty();
  }
}
