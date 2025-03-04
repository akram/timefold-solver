package ai.timefold.solver.core.impl.bavet.uni;

import static ai.timefold.solver.core.impl.bavet.uni.Group1Mapping0CollectorUniNode.createGroupKey;

import java.util.function.Function;

import ai.timefold.solver.core.api.score.stream.uni.UniConstraintCollector;
import ai.timefold.solver.core.config.solver.EnvironmentMode;
import ai.timefold.solver.core.impl.bavet.common.tuple.QuadTuple;
import ai.timefold.solver.core.impl.bavet.common.tuple.TupleLifecycle;
import ai.timefold.solver.core.impl.util.Triple;

public final class Group1Mapping3CollectorUniNode<OldA, A, B, C, D, ResultContainerB_, ResultContainerC_, ResultContainerD_>
        extends AbstractGroupUniNode<OldA, QuadTuple<A, B, C, D>, A, Object, Triple<B, C, D>> {

    private final int outputStoreSize;

    public Group1Mapping3CollectorUniNode(Function<OldA, A> groupKeyMapping,
            int groupStoreIndex, int undoStoreIndex,
            UniConstraintCollector<OldA, ResultContainerB_, B> collectorB,
            UniConstraintCollector<OldA, ResultContainerC_, C> collectorC,
            UniConstraintCollector<OldA, ResultContainerD_, D> collectorD,
            TupleLifecycle<QuadTuple<A, B, C, D>> nextNodesTupleLifecycle, int outputStoreSize,
            EnvironmentMode environmentMode) {
        super(groupStoreIndex, undoStoreIndex,
                tuple -> createGroupKey(groupKeyMapping, tuple),
                Group0Mapping3CollectorUniNode.mergeCollectors(collectorB, collectorC, collectorD), nextNodesTupleLifecycle,
                environmentMode);
        this.outputStoreSize = outputStoreSize;
    }

    @Override
    protected QuadTuple<A, B, C, D> createOutTuple(A a) {
        return new QuadTuple<>(a, null, null, null, outputStoreSize);
    }

    @Override
    protected void updateOutTupleToResult(QuadTuple<A, B, C, D> outTuple, Triple<B, C, D> result) {
        outTuple.factB = result.a();
        outTuple.factC = result.b();
        outTuple.factD = result.c();
    }

}
