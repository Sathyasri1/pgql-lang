/*
 * Copyright (C) 2013 - 2023 Oracle and/or its affiliates. All rights reserved.
 */
package oracle.pgql.lang.ir.modify;

import java.util.List;

import oracle.pgql.lang.ir.QueryEdge;
import oracle.pgql.lang.ir.QueryExpression;
import oracle.pgql.lang.ir.QueryExpressionVisitor;

import static oracle.pgql.lang.ir.PgqlUtils.printIdentifier;

public class EdgeInsertion extends AbstractInsertion {

  private QueryEdge edge;

  public EdgeInsertion(QueryEdge edge, List<QueryExpression> labels, List<SetPropertyExpression> properties) {
    super(labels, properties);
    this.edge = edge;
  }

  public QueryEdge getEdge() {
    return edge;
  }

  public void setEdge(QueryEdge edge) {
    this.edge = edge;
  }

  @Override
  public InsertionType getInsertionType() {
    return InsertionType.EDGE_INSERTION;
  }

  @Override
  public String toString() {
    return "EDGE " + printIdentifier(edge.getName(), false) + " BETWEEN "
        + printIdentifier(edge.getSrc().getName(), false) + " AND " + printIdentifier(edge.getDst().getName(), false)
        + printLabels() + printProperties();
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj))
      return false;
    EdgeInsertion other = (EdgeInsertion) obj;
    if (edge == null) {
      if (other.edge != null)
        return false;
    } else if (!edge.equals(other.edge))
      return false;
    return true;
  }

  @Override
  public void accept(QueryExpressionVisitor v) {
    v.visit(this);
  }
}
