package vaadin.scala.tests

import vaadin.scala.HierarchicalContainer
import vaadin.scala.mixins.HierarchicalContainerMixin
import org.mockito.Mockito

class HierarchicalContainerTests extends ScaladinTestSuite {

  class VaadinHierarchicalContainer extends com.vaadin.data.util.HierarchicalContainer with HierarchicalContainerMixin

  var vaadinContainer: VaadinHierarchicalContainer = _
  var spy: VaadinHierarchicalContainer = _
  var container: HierarchicalContainer = _

  var mockedVaadinContainer: VaadinHierarchicalContainer = _
  var containerWithMock: HierarchicalContainer = _

  before {
    vaadinContainer = new VaadinHierarchicalContainer
    spy = Mockito.spy(vaadinContainer)
    container = new HierarchicalContainer(spy)

    mockedVaadinContainer = mock[VaadinHierarchicalContainer]
    containerWithMock = new HierarchicalContainer(mockedVaadinContainer)
  }

  test("moveAfterSibling") {
    containerWithMock.moveAfterSibling('itemId, 'siblingId)
    Mockito.verify(mockedVaadinContainer).moveAfterSibling('itemId, 'siblingId)
  }

  test("removeItemRecursively") {
    containerWithMock.removeItemRecursively('itemId)
    Mockito.verify(mockedVaadinContainer).removeItemRecursively('itemId)
  }

  test("includeParentsWhenFiltering") {
    assert(container.includeParentsWhenFiltering)
    container.includeParentsWhenFiltering = false
    assert(!container.includeParentsWhenFiltering)
    Mockito.verify(spy).setIncludeParentsWhenFiltering(false)
    Mockito.verify(spy, Mockito.times(2)).isIncludeParentsWhenFiltering()
  }

}