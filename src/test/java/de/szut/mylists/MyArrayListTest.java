package de.szut.mylists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MyArrayListTest {
    MyArrayList list;

    void fillListWithItems(int n) {
        for (int i = 0; i < n; i++) {
            list.add(i + 1);
        }
    }

    @BeforeEach
    void setup() {
        this.list = new MyArrayList();
    }

    @Test
    void givenEmptyList_whenSize_returnZero() {
        var size = list.size();

        assertThat(size).isEqualTo(0);
    }

    @Test
    void givenListWithOneItem_whenSize_returnOne() {
        list.add(1);
        var size = list.size();

        assertThat(size).isEqualTo(1);
    }

    @Test
    void givenListWithTwoItems_whenSize_returnTwo() {
        fillListWithItems(2);

        var size = list.size();

        assertThat(size).isEqualTo(2);
    }

    @Test
    void givenListWithOneItem_whenGet0_returnCorrectItem() {
        list.add(1);

        var item = list.get(0);

        assertThat(item).isEqualTo(1);
    }

    @Test
    void givenListWithTwoItems_whenGet1_returnCorrectItem() {
        fillListWithItems(2);

        var item = list.get(1);

        assertThat(item).isEqualTo(2);
    }

    @Test
    void givenListWithTwoItems_whenGet3_throwOutOfBoundsException() {
        fillListWithItems(2);

        assertThatThrownBy(() -> {
            list.get(3);
        }).isInstanceOf(RuntimeException.class).hasMessage("Dieser Index existiert nicht!");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 8, 10, 15, 25, 35, 1000, 10000})
    void givenNItems_whenGetN_returnCorrectLastItem(int n) {
        fillListWithItems(n);

        var item = list.get(n - 1);

        assertThat(item).isEqualTo(n);
    }

    @Test
    void givenListWithOneItem_whenRemove_returnSize0() {
        fillListWithItems(1);

        list.remove(0);

        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    void givenListWithTwoItems_whenRemoveItem2_returnSize1() {
        fillListWithItems(2);

        list.remove(1);

        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void givenListWithTwoItems_whenRemoveItem1AndGetIndex0_return2() {
        fillListWithItems(2);

        list.remove(0);

        assertThat(list.get(0)).isEqualTo(2);
    }

    @Test
    void givenListWithFiveItems_whenRemoveIndex3AndGetIndex3_return5() {
        fillListWithItems(5);

        list.remove(3);

        assertThat(list.get(3)).isEqualTo(5);
    }

    @Test
    void givenListWithFiveItems_whenRemoveIndexSix_returnException() {
        fillListWithItems(5);

        assertThatThrownBy(() -> {
            list.remove(6);
        }).isInstanceOf(RuntimeException.class).hasMessage("Dieser Index existiert nicht!");
    }

    @Test
    void givenListWithFiveItems_whenContains3_returnTrue() {
        fillListWithItems(5);

        var result = list.contains(3);

        assertThat(result).isTrue();
    }

    @Test
    void givenListWithFiveItems_whenContains6_returnFalse() {
        fillListWithItems(5);

        var result = list.contains(6);

        assertThat(result).isFalse();
    }
}
