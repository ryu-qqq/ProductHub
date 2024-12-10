package com.ryuqq.setof.enums.core;

/**
 * Represents the lifecycle of a product in the system.
 * <p>
 * This enum defines the various states a product can be in during its lifecycle,
 * from initial registration to final approval or failure. The lifecycle is managed
 * through batch processing and Airflow workflows that extract, process, and analyze product data.
 * </p>
 *
 * <pre>
 * WAITING -> PROCESSING -> GATHERING -> REVIEW -> APPROVED / FAILED
 * </pre>
 *
 * <ul>
 *     <li>{@link #WAITING}: The product has been initially registered, and only the
 *     {@code ProductGroup} is stored in the database. The rest of the product data
 *     is temporarily stored in NoSQL.</li>
 *
 *     <li>{@link #PROCESSING}: The product is selected by a batch job and its data
 *     is being moved from the NoSQL store to the relational database.</li>
 *
 *     <li>{@link #GATHERING}: The product is undergoing data extraction and transformation
 *     by Airflow, where additional details are being gathered and processed.</li>
 *
 *     <li>{@link #REVIEW}: The Airflow process has completed its analysis and has sent
 *     the product data for review.</li>
 *
 *     <li>{@link #APPROVED}: The product has passed all validations and received final approval.</li>
 *
 *     <li>{@link #FAILED}: The product has encountered an error during one of the stages
 *     and the process has failed.</li>
 * </ul>
 *
 * <p>Utility methods are provided to check specific statuses:
 * {@link #isApproved()}, {@link #isFailed()}.</p>
 *
 * @author Your Name
 * @version 1.0
 * @since 2024-01-01
 */
public enum ProductStatus {

    /**
     * The product has been initially registered.
     * Only the {@code ProductGroup} has been saved in the database, and the remaining data
     * is stored in a NoSQL database temporarily.
     */
    WAITING,

    /**
     * The product is being processed by a batch job.
     * The batch selects products in the {@link #WAITING} state and transfers their data
     * from the NoSQL database to the relational database.
     */
    PROCESSING,

    /**
     * The product is undergoing data extraction and transformation.
     * Airflow is actively processing the product to gather additional information and prepare it for analysis.
     */
    GATHERING,

    /**
     * The product has been processed and analyzed.
     * Airflow has completed its task and sent the product data for review.
     */
    REVIEW,

    /**
     * The product has been approved.
     * All validations have been successfully completed, and the product is ready for further operations.
     */
    APPROVED,

    /**
     * The product has failed during processing.
     * An error occurred during one of the stages, and the product cannot proceed further.
     */
    FAILED;

    /**
     * Checks if the current status is {@link #APPROVED}.
     *
     * @return {@code true} if the status is {@link #APPROVED}, otherwise {@code false}.
     */
    public boolean isApproved() {
        return this.equals(APPROVED);
    }

    /**
     * Checks if the current status is {@link #FAILED}.
     *
     * @return {@code true} if the status is {@link #FAILED}, otherwise {@code false}.
     */
    public boolean isFailed() {
        return this.equals(FAILED);
    }
}