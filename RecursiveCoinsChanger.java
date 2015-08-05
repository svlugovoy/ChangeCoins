public class RecursiveCoinsChanger {

    private static int[] coinNominals = new int[]{1, 2, 5, 10, 25, 50};

    public static void main(String[] args) {
        System.out.println(calcVariants(5));
        System.out.println(calcVariants(10));
        System.out.println(calcVariants(100));
    }

    public static int calcVariants(int cashValue) {
        return findCombination(coinNominals.length - 1, cashValue, new int[coinNominals.length]);
    }

    private static int findCombination(int nominalIndex, int cashValue, int[] combinations) {
        int sum = 0;
        int variantsCount = 0;
        while (sum < cashValue) {
            if (nominalIndex > 0) {
                variantsCount += findCombination(nominalIndex - 1, cashValue, combinations);
                clearLowNominalsCount(nominalIndex, combinations);
            }
            combinations[nominalIndex]++;
            sum = calcSum(combinations);
            if (sum == cashValue) {
                variantsCount++;
            }
        }

        return variantsCount;
    }

    private static void clearLowNominalsCount(int nominalIndex, int[] combinations) {
        for (int j = 0; j < nominalIndex; j++) {
            combinations[j] = 0;
        }
    }

    private static int calcSum(int[] combinations) {
        int sum = 0;
        for (int i = 0; i < combinations.length; i++) {
            sum = sum + combinations[i] * coinNominals[i];
        }
        return sum;
    }
}

