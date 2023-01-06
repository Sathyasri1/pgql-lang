/*
 * Copyright (C) 2013 - 2023 Oracle and/or its affiliates. All rights reserved.
 */
package oracle.pgql.lang.editor.completion;

import java.util.List;

public interface PgqlCompletionContext {

  List<String> getGraphNames();

  List<String> getVertexProperties(String graphName);

  List<String> getEdgeProperties(String graphName);

  List<String> getVertexLabels(String graphName);

  List<String> getEdgeLabels(String graphName);
}
