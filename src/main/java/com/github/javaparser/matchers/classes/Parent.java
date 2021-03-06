package com.github.javaparser.matchers.classes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.matchers.MatchContext;
import com.github.javaparser.matchers.MatchResult;
import com.github.javaparser.matchers.Matcher;

/**
 * This Matcher will be satisfied if at the parent of the node satisfies the parentMatcher.
 */
public class Parent<N extends Node> implements Matcher<N> {

    private Matcher<N> parentMatcher;

    public Parent(Matcher<N> parentMatcher) {
        this.parentMatcher = parentMatcher;
    }

    @Override
    public MatchResult<N> match(N node, MatchContext matchContext) {
        return node.getParentNode().isPresent() ?
                parentMatcher.match((N)node.getParentNode().get(), matchContext).currentNode(node) :
                MatchResult.empty(node);
    }
}
