package com.ryuqq.setof.enums.core;

/**
 * Represents the synchronization status in the external mall integration lifecycle.
 * <p>
 * This enum defines the various stages of the synchronization process between internal products
 * and external malls. Each status indicates a specific point in the process, and the flow of statuses
 * follows the lifecycle:
 * </p>
 *
 * <pre>
 * WAITING -> SYNC_REQUIRED -> PROCESSING -> REVIEW -> APPROVED / FAILED
 * </pre>
 *
 * <ul>
 *     <li>{@link #WAITING}: The synchronization task is in a waiting state. This is the initial state
 *     when a task has not started yet, or when a new product registration is pending.</li>
 *
 *     <li>{@link #SYNC_REQUIRED}: Synchronization is required due to updates in the internal product data.
 *     This status indicates that external synchronization needs to be performed.</li>
 *
 *     <li>{@link #PROCESSING}: The synchronization task is in progress. Communication with the external mall
 *     is actively ongoing at this stage.</li>
 *
 *     <li>{@link #REVIEW}: The synchronization task has completed. The data has been sent to the external mall
 *     and is undergoing internal verification.</li>
 *
 *     <li>{@link #APPROVED}: The synchronization task has been approved. All validations have passed, and the
 *     process has successfully completed.</li>
 *
 *     <li>{@link #FAILED}: The synchronization task has failed. Errors occurred during the process, causing
 *     the task to terminate.</li>
 * </ul>
 *
 * <p>Use the utility methods provided to check the specific status:
 * {@link #isApproved()}, {@link #isSyncRequired()}.</p>
 *
 * @author Your Name
 * @version 1.0
 * @since 2024-01-01
 */
public enum SyncStatus {

    /**
     * Synchronization task is waiting.
     * The task is in an initial state and has not started yet,
     * or a newly registered product is awaiting synchronization.
     */
    WAITING,

    /**
     * Synchronization is required.
     * Indicates that updates in internal product data necessitate external synchronization.
     */
    SYNC_REQUIRED,

    /**
     * Synchronization task is in progress.
     * Active communication with the external mall is ongoing.
     */
    PROCESSING,

    /**
     * Synchronization task is under review.
     * Internal validation is underway after data has been sent to the external mall.
     */
    REVIEW,

    /**
     * Synchronization task is approved.
     * The process has completed successfully with all validations passed.
     */
    APPROVED,

    /**
     * Synchronization task has failed.
     * Errors occurred during the process, leading to termination.
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
     * Checks if the current status is {@link #SYNC_REQUIRED}.
     *
     * @return {@code true} if the status is {@link #SYNC_REQUIRED}, otherwise {@code false}.
     */
    public boolean isSyncRequired() {
        return this.equals(SYNC_REQUIRED);
    }

}