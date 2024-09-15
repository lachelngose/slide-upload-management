package org.example.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import software.amazon.awssdk.transfer.s3.S3TransferManager
import software.amazon.awssdk.transfer.s3.model.CompletedFileDownload
import software.amazon.awssdk.transfer.s3.model.CompletedFileUpload
import software.amazon.awssdk.transfer.s3.model.DownloadFileRequest
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest
import software.amazon.awssdk.transfer.s3.progress.LoggingTransferListener
import java.nio.file.Paths

@Service
class S3TransferService(private val s3TransferManager: S3TransferManager) {
    @Value("\${spring.cloud.aws.s3.bucket}")
    lateinit var bucketName: String

    fun uploadFile(key: String, filePath: String): CompletedFileUpload? {
        return uploadFileWithTransferManager(s3TransferManager, bucketName, key, filePath)
    }

    fun downloadFile(key: String, downloadPath: String): CompletedFileDownload? {
        return downloadFileWithTransferManager(s3TransferManager, bucketName, key, downloadPath)
    }

    private fun uploadFileWithTransferManager(
        transferManager: S3TransferManager,
        bucketName: String,
        key: String,
        filePath: String
    ): CompletedFileUpload? {
        val uploadRequest = UploadFileRequest.builder()
            .source(Paths.get(filePath))
            .putObjectRequest { requestBuilder ->
                requestBuilder.bucket(bucketName)
                    .key(key)
            }
            .addTransferListener(LoggingTransferListener.create())
            .build()

        val upload = transferManager.uploadFile(uploadRequest)
        return upload.completionFuture().join()
    }

    private fun downloadFileWithTransferManager(
        transferManager: S3TransferManager,
        bucketName: String,
        key: String,
        downloadPath: String
    ): CompletedFileDownload? {
        val downloadRequest = DownloadFileRequest.builder()
            .destination(Paths.get(downloadPath))
            .getObjectRequest { requestBuilder ->
                requestBuilder.bucket(bucketName)
                    .key(key)
            }
            .addTransferListener(LoggingTransferListener.create())
            .build()

        val download = transferManager.downloadFile(downloadRequest)

        return download.completionFuture().join()
    }
}